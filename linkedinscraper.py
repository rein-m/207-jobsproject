import time
import csv
import sys
import requests
import random
import undetected_chromedriver as uc
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
from selenium.common.exceptions import TimeoutException, WebDriverException, NoSuchElementException


#function to log in to li; currently fails if captcha
def login_to_linkedin(driver):
    print("\nlogging in")
    
    driver.get("https://www.linkedin.com/login")
    wait = WebDriverWait(driver, 30)

    try:
        email_field = wait.until(EC.presence_of_element_located((By.ID, "username")))
        password_field = wait.until(EC.presence_of_element_located((By.ID, "password")))
        
        email_field.send_keys(LINKEDIN_EMAIL)
        password_field.send_keys(LINKEDIN_PASSWORD)
        
        sign_in_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
        sign_in_button.click()
        
        wait.until(
            EC.presence_of_element_located((By.ID, "global-nav-typeahead"))
        )
        
        
    except TimeoutException:
        print("timeout fail")
        return False
    except Exception as e:
        print(f"login failed due to error: {e}")
        return False
        
    return True


def scrape_job_details(driver, job_id):
    url = f"https://www.linkedin.com/jobs/view/{job_id}/"
    print(f"Navigating to: {url}")
    
    try:
        driver.get(url)
        wait = WebDriverWait(driver, 30)

        # wait for main card
        wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".t-14.artdeco-card")))
        time.sleep(random.uniform(1.0, 2.5)) 

        # get page source
        soup = BeautifulSoup(driver.page_source, "html.parser")
        
        job_data = {"id": job_id, "url": url}

        # scrape title
        try:
            job_data["title"] = soup.find("h1").get_text(strip=True) if soup.find("h1") else None
        except Exception:
            job_data["title"] = None

        # scrape company and location
        try:
            company_container = soup.find("div", class_="display-flex align-items-center flex-1")
            if company_container:
                company_tag = company_container.find("a")
                if company_tag and company_tag.has_attr('aria-label'):
                    # get name from the logo label
                    job_data["company"] = company_tag['aria-label'].replace(" logo", "")
                else:
                    job_data["company"] = None
            else:
                job_data["company"] = None

            tertiary_desc_container = soup.find("div", class_="job-details-jobs-unified-top-card__tertiary-description-container")
            if tertiary_desc_container:
                location_tag = tertiary_desc_container.find("span", class_="tvm__text tvm__text--low-emphasis")
                if location_tag:
                    job_data["location"] = location_tag.get_text(strip=True)
                else:
                    job_data["location"] = None
            else:
                job_data["location"] = None

        except Exception as e:
            print(f"Error scraping company/location: {e}")
            job_data["company"] = None
            job_data["location"] = None

        # scrape full description
        try:
            description_container = soup.find("div", id="job-details")
            
            if description_container:
                job_data["description"] = description_container.get_text(separator="\n", strip=True)
            else:
                print("Could not find description div")
                job_data["description"] = None
                
        except Exception as e:
            print(f"Error scraping description: {e}")
            job_data["description"] = None

        return job_data

    except TimeoutException:
        print(f"Timeout while loading ID {job_id}, skipping")
        return None
    except Exception as e:
        print(f"Error while scraping ID {job_id}: {e}, skipping")
        return None


def get_jobs_from_list_linkedin(jobs_url: str):
    options = uc.ChromeOptions()
    # options.add_argument('--headless') # headless if desired
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')
    user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.Nothing Safari/537.36"
    options.add_argument(f'user-agent={user_agent}')

    driver = None
    all_job_data = []
    job_ids = []

    try:
        driver = uc.Chrome(options=options)
        
        if not login_to_linkedin(driver):
            print("Skipping job search due to login failure.")
            if driver:
                driver.quit()
            return [] # return empty list on login failure

        driver.get(jobs_url)
        
        wait = WebDriverWait(driver, 30)
        locator = (By.CSS_SELECTOR, "li[data-occludable-job-id]")
        
        wait.until(EC.presence_of_element_located(locator))
        time.sleep(2)
        
        html_source = driver.page_source
        soup = BeautifulSoup(html_source, "html.parser")

        job_cards = soup.find_all('li', attrs={'data-occludable-job-id': True})
        job_ids = [card.get('data-occludable-job-id') for card in job_cards]

        if not job_ids:
            return []
        
        # do a cool down pause
        sleep_time = random.uniform(1.0, 4.0)
        time.sleep(sleep_time)
        
        for job_id in job_ids:
            details = scrape_job_details(driver, job_id)
            if details:
                all_job_data.append(details)
            # wait for a bit to hopefully not proc any detection
            sleep_time = random.uniform(2.0, 5.0)
            time.sleep(sleep_time)


    except TimeoutException:
        print("\nTimeout Error")
    except WebDriverException as e:
        print(f"\nWebDriver Error: {e}")
    except Exception as e:
        print(f"\nAn unexpected error occurred: {e}")
    
    finally:
        # causes a nonmajor crash but it's a known bug afaik
        if driver:
            driver.quit()
    
    # return all job data dictionaries
    return all_job_data


if __name__ == "__main__":
    keyword1 = "software" # no spaces, one word
    keyword2 = "engineer" # no spaces, one word
    
    # don't be dumb, make a burner account for this. 
    LINKEDIN_EMAIL = "" # your linkedin email here
    LINKEDIN_PASSWORD = "" # your linkedin password here
    SEARCH_URL = f"https://www.linkedin.com/jobs/search/?keywords={keyword1}%20{keyword2}"

    scraped_data = get_jobs_from_list_linkedin(SEARCH_URL)

    if scraped_data:
        print("scraping done, saving to csv")
        try:
            csv_file = 'linkedin_jobs.csv'
            headers = scraped_data[0].keys()

            with open(csv_file, mode='w', encoding='utf-8') as file:
                writer = csv.DictWriter(file, fieldnames=headers)
                writer.writeheader()
                writer.writerows(scraped_data)
            
            print("data saved, now go do leetcode")

        except Exception as e:
            print(f"Error saving to CSV: {e}")

        




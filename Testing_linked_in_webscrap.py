import requests
from bs4 import BeautifulSoup, Comment
import pandas as pd
import time 
import json

def scrape_linkedin_job_description(url: str) -> str | None:
    """
    Scrape the main job-description text from a LinkedIn job posting.

    Returns the description as a string, or None if it can't be found.
    """
    # Use a browser-like header so the request isn't rejected immediately
    headers = {
        "User-Agent": (
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
            "AppleWebKit/537.36 (KHTML, like Gecko) "
            "Chrome/129.0.0.0 Safari/537.36"
        ),
        "Accept-Language": "en-US,en;q=0.9",
    }

    response = requests.get(url, headers=headers)
    response.raise_for_status()

    soup = BeautifulSoup(response.text, "html.parser")

    # This targets the <div> shown in your screenshot:
    # <div class="show-more-less-html__markup show-more-less-html__markup--clamp-after-5 relative overflow-hidden">
    desc_div = soup.find(
        "div",
        class_="show-more-less-html__markup show-more-less-html__markup--clamp-after-5 relative overflow-hidden",
    )

    # If that exact combo isn’t present, fall back to the base class
    if desc_div is None:
        desc_div = soup.find("div", class_="show-more-less-html__markup")

    if desc_div is None:
        return None

    # Get clean text from the HTML, preserving line breaks a bit
    description_text = desc_div.get_text(separator="\n", strip=True)
    return description_text


class Company:
    
    def __init__(self, name):
        self.name = name
        self.url = f'https://www.linkedin.com/company/{name}/'
        # self.basic_info = self.overview()
        # self.data = self.get_data()
        # self.jobs = self.job_postings()
        self.all_data = {'Company Description': self.overview(), 'Company Details': self.get_data(), "Company Job Postings": self.job_postings()}
        #self.website = data["website"]; self.industry = data['industry']; self.size = data['size']; self.headquarters = data['headquarters']; self.type = data['type']; self.founded = data['founded']; self.specialities = data['specialities']

    def __str__(self):
        print(self.all_data)

    def overview(self):
        headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
        "Accept-Language": "en-US,en;q=0.9",
        "Accept-Encoding": "gzip, deflate, br",
        "Connection": "keep-alive",
        }
        params = {"q": "python webscraping"}
        response = requests.get(self.url, headers=headers, params=params)
        soup = BeautifulSoup(response.content, "html.parser")
        data = soup.find('p', class_='break-words text-color-text').get_text(strip=True)
        return data
    
    def get_data(self):
        headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
        "Accept-Language": "en-US,en;q=0.9",
        "Accept-Encoding": "gzip, deflate, br",
        "Connection": "keep-alive",
        }
        params = {"q": "python webscraping"}
        response = requests.get(self.url, headers=headers, params=params)
        soup = BeautifulSoup(response.content, "html.parser")
        return {'website': soup.find("div", {"data-test-id": "about-us__website"}).find('a', class_='link-no-visited-state hover:no-underline').get_text(strip=True), 
                'industry': soup.find("div", {"data-test-id": "about-us__industry"}).find(class_='font-sans px-0.25 text-md text-color-text break-words overflow-hidden').get_text(strip=True),
                'size': soup.find("div", {"data-test-id": "about-us__size"}).find(class_='font-sans px-0.25 text-md text-color-text break-words overflow-hidden').get_text(strip=True),
                "headquarters": soup.find("div", {"data-test-id": "about-us__headquarters"}).find(class_='font-sans px-0.25 text-md text-color-text break-words overflow-hidden').get_text(strip=True),
                'type': soup.find("div", {"data-test-id": "about-us__organizationType"}).find(class_='font-sans px-0.25 text-md text-color-text break-words overflow-hidden').get_text(strip=True),
                'specialities': soup.find("div", {"data-test-id": "about-us__specialties"}).find(class_='font-sans px-0.25 text-md text-color-text break-words overflow-hidden').get_text(strip=True)}

    def job_postings(self):
        url = f'https://www.linkedin.com/jobs/{self.name}-jobs-worldwide?f_C=165158&trk=job-results_see-all-jobs-link&position=1&pageNum=0'
        # 'https://www.linkedin.com/jobs/netflix-jobs-worldwide?f_C=165158&trk=job-results_see-all-jobs-link&position=1&pageNum=0'
        headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
        "Accept-Language": "en-US,en;q=0.9",
        "Accept-Encoding": "gzip, deflate, br",
        "Connection": "keep-alive",
        }
        params = {"q": "python webscraping"}
        response = requests.get(url, headers=headers, params=params)
        soup = BeautifulSoup(response.content, "html.parser")
        
        job_nums = []
        all_jobs = soup.find_all('div', class_='base-card relative w-full hover:no-underline focus:no-underline base-card--link base-search-card base-search-card--link job-search-card')
        for job in all_jobs:
            job_nums.append(job.get("data-entity-urn").split(":")[3])
        print(job_nums)

        jobs_final = []
        for job_page in job_nums:
            try:
                url = f"https://www.linkedin.com/jobs/view/{job_page}"
                print(url)
                response = requests.get(url, headers=headers, params=params)
                soup = BeautifulSoup(response.content, "html.parser")

                jobs_final.append({'title': soup.find('h1', class_ = "top-card-layout__title font-sans text-lg papabear:text-xl font-bold leading-open text-color-text mb-0 topcard__title").get_text(strip=True),
                                'location': soup.find('span', class_ = "topcard__flavor topcard__flavor--bullet").get_text(strip=True),
                                'pay range': soup.find('div', class_ = "salary compensation__salary").get_text(strip=True),
                                'description': scrape_linkedin_job_description(url),
                                "employment type": soup.find('span', class_ = "description__job-criteria-text description__job-criteria-text--criteria").get_text(strip=True)
                                })
            except AttributeError:
                print(url + ' -- NOT AVALIABLE')
                pass
        
            
        return jobs_final
    
# class Returned_files():
#     def __init__(self, company_names):
#         self.all_comp_names = company_names
#         self.companies = self.getcompanies()

#     def getcompanies(self):
#         all_companies = []
#         for company in self.all_comp_names:
#             all_companies.append(Company(company))
#         return all_companies
    
#     def returnJSON(self):
        
#         """
#         Save a Python dictionary to a JSON file.

#         :param data: The dictionary to save.
#         :param filename: Output filename ending in .json
#         """

#         with open('all company data', "w", encoding="utf-8") as f:
#             json.dump(self.companies, f, indent=4, ensure_ascii=False)

#         # print(f"JSON file saved as: {filename}")

if __name__ == "__main__":
    # Returned_files('netflix').returnJSON()
    json_file = {'netflix': Company('netflix').all_data,'apple': Company('apple').all_data,'amazon': Company('amazon').all_data,'meta': Company('meta').all_data,'google': Company('google').all_data}

    with open('faang data', "w", encoding="utf-8") as f:
            json.dump(json_file, f, indent=4, ensure_ascii=False)
    


    #  company = Company('netflix')
    # print(company.jobs)

    # headers = {
    #     "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
    #     "Accept-Language": "en-US,en;q=0.9",
    #     "Accept-Encoding": "gzip, deflate, br",
    #     "Connection": "keep-alive",
    #     }
    # params = {"q": "python webscraping"}
    # jobs_final = []
    # url = "https://www.linkedin.com/jobs/view/4333253325"
    # # print(url)
    # response = requests.get(url, headers=headers, params=params)
    # soup = BeautifulSoup(response.content, "html.parser")

    # # description = 

    # jobs_final.append({'title': soup.find('h1', class_ = "top-card-layout__title font-sans text-lg papabear:text-xl font-bold leading-open text-color-text mb-0 topcard__title").get_text(strip=True),
    #                     'location': soup.find('span', class_ = "topcard__flavor topcard__flavor--bullet").get_text(strip=True),
    #                     'pay range': soup.find('div', class_ = "salary compensation__salary").get_text(strip=True),
    #                     'description': scrape_linkedin_job_description(url),
    #                     "employment type": soup.find('span', class_ = "description__job-criteria-text description__job-criteria-text--criteria").get_text(strip=True)
    #                     })
    
    # print(jobs_final[0])
    
    # url = "https://www.linkedin.com/jobs/view/4333253325"
    # headers = {
    #     "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
    #     "Accept-Language": "en-US,en;q=0.9",
    #     "Accept-Encoding": "gzip, deflate, br",
    #     "Connection": "keep-alive",
    #     }
    # params = {"q": "python webscraping"}
    # response = requests.get(url, headers=headers, params=params)
    # time.sleep(2)
    # soup = BeautifulSoup(response.content, "html.parser")
    # desc_div = soup.find(
    #     "div",
    #     class_="show-more-less-html__markup show-more-less-html__markup--clamp-after-5 relative overflow-hidden",
    # )

    # # If that exact combo isn’t present, fall back to the base class
    # if desc_div is None:
    #     desc_div = soup.find("div", class_="show-more-less-html__markup")

    # if desc_div is None:
    #     print(None)

    # # Get clean text from the HTML, preserving line breaks a bit
    # description_text = desc_div.get_text(separator="\n", strip=True)
    # print( description_text)
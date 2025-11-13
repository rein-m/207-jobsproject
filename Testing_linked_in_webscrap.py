import requests
from bs4 import BeautifulSoup, Comment
import pandas as pd

class Company:
    
    def __init__(self, name):
        self.name = name
        self.url = f'https://www.linkedin.com/company/{name}'
        self.basic_info = self.overview()
        self.data = self.get_data()
        # self.jobs = self.job_postings()
        #self.website = data["website"]; self.industry = data['industry']; self.size = data['size']; self.headquarters = data['headquarters']; self.type = data['type']; self.founded = data['founded']; self.specialities = data['specialities']

    def __str__(self):
        print(self.data)

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

    # def job_postings(self):
    #     url = f'https://www.linkedin.com/jobs/search?keywords={self.name}&location=United%2BStates&geoId=103644278'
    #     headers = {
    #     "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
    #     "Accept-Language": "en-US,en;q=0.9",
    #     "Accept-Encoding": "gzip, deflate, br",
    #     "Connection": "keep-alive",
    #     }
    #     params = {"q": "python webscraping"}
    #     response = requests.get(url, headers=headers, params=params)
    #     soup = BeautifulSoup(response.content, "html.parser")
        
    #     job_nums = []
    #     all_jobs = soup.find_all('div', class_='base-card relative w-full hover:no-underline focus:no-underline base-card--link base-search-card base-search-card--link job-search-card')
    #     for job in all_jobs:
    #         job_nums.append(job.get("data-entity-urn").split(":")[3])

    #     jobs_final = []
    #     for job_page in job_nums:
    #         url = f"https://www.linkedin.com/jobs/view/{job_page}"
    #         print(url)
    #         response = requests.get(url, headers=headers, params=params)
    #         soup = BeautifulSoup(response.content, "html.parser")
    #         jobs_final.append({'title': soup.find('h1', class_ = "top-card-layout__title font-sans text-lg papabear:text-xl font-bold leading-open text-color-text mb-0 topcard__title").get_text(strip=True),
    #                            'location': soup.find('span', class_ = "topcard__flavor topcard__flavor--bullet").get_text(strip=True),
    #                            'pay range': soup.find('div', class_ = "salary compensation__salary").get_text(strip=True),
    #                         #    'description': soup.find('div', class_ = "show-more-less-html__markup relative overflow-hidden").get_text(strip=True),
    #                            "employment type": soup.find('span', class_ = "description__job-criteria-text description__job-criteria-text--criteria").get_text(strip=True)
    #                            })
            
    #     return jobs_final

if __name__ == "__main__":
    companies = {}
    faang = ['apple','meta','amazon','netflix','google']
    for company in faang:
        print(company)
        companies[company] = Company(company)

    print(companies)
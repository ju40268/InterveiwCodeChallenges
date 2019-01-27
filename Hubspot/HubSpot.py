#!/usr/bin/python3
import requests
import json
from datetime import datetime 

def check_connsecutive(date1, date2):
    """
    Function to check whether the two input dates are connsecutive
        :param date1: input date1
        :param date2: input date2
        :return boolean: whether two dates are connsecutive
    """
    date1 = datetime.strptime(date1, "%Y-%m-%d")
    date2 = datetime.strptime(date2, "%Y-%m-%d")
    return abs((date2 - date1).days) == 1

def get_data(sourceUrl):
    """
    Function to send Http get and retrieve result
        :param sourceUrl: url to get data
        :return data: data in json format
    """
    data = requests.get(sourceUrl).json()
    return data

def post_result(results, destinationUrl):
    """
    Function to post result back to Hubspot server
        :param results: calculated result
        :param destinationUrl: url to post data
        :return: void
    """
    response = requests.post(url = destinationUrl, data = json.dumps(results))
    print("Response Body : ", response.content)
    print("Status Code : ", response.status_code)   

def get_max_overlap(cur_country, dates_available):
    """
    Function to calculate the dates that could accommondate most countries
    if the number is more than the original date, replace it with new date
        :param cur_country: current country for consideration
        :param dates_available: the preprocessed, all available dates
        :return cur_entry: dict recording the result for current country
    """
    best_start_date = ""
    all_attendees = []
    for date, attendees in dates_available.items():
        if len(attendees) > len(all_attendees):
            best_start_date = date
            all_attendees = attendees
    cur_entry = {}
    cur_entry["attendeeCount"] = len(all_attendees)
    cur_entry["attendees"] = all_attendees
    cur_entry["name"] = cur_country
    if best_start_date != "":
        cur_entry["startDate"] = best_start_date
    else:
        cur_entry["startDate"] = None
    return cur_entry

def calculate_partners(data):
    """
    Function to parse data, and geather all result
        :param data: input data in json format
        :return result: all result according to all countries
    """
    countries = set()
    for partner in data["partners"]:
        countries.add(partner["country"])
    


    """
    Collect the dates in a row and email lists of their according available partners. 
    If they are connsecutive, record the dates and calculate the overlap to get the result entry for every country.
    """
    results = {"countries":[]}
    for country in countries:
        partner_from_same_country = []
        for partner in data["partners"]:
            if partner["country"] == country:
                partner_from_same_country.append(partner)
        dates_available = {}
        for partner in partner_from_same_country:
            all_dates = partner["availableDates"]
            for index in range(len(all_dates) - 1):
                if check_connsecutive(all_dates[index], all_dates[index + 1]):
                    dates_available[all_dates[index]] = dates_available.get(all_dates[index], []) + [partner["email"]]
        cur_entry = get_max_overlap(country, dates_available)
        results["countries"].append(cur_entry)
    return results


def main():
    sourceUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=82acbb877a8b26c81ac0fe4015e3"
    destinationUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=82acbb877a8b26c81ac0fe4015e3"
    data = get_data(sourceUrl)
    results = calculate_partners(data)
    post_result(results, destinationUrl)

if __name__ == "__main__":
    """
    Hubspot Code Challenge
    @Author: Christie Chen
    """
    main()
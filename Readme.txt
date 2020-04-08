This is a Web Crawling application which is a spring  boot application. This application has a rest endpoint which is a post  request.
This request takes in 2 parameters which are url and depth.
Connection to given url is made using Jsoup.
Output will be total no. of links crawled, total no. of images crawled and detail of each url crawled.
Enhencement done: in detailed link node added two more parameters as :
1) depth: will tell that the link crawled is at what depth
2) total links: will tell how many links exists on  that url


Sample request:

Endpoint: http://localhost:8080/crawler
RequestBody:
{
	"url":"https://www.geeksforgeeks.org/",
	"depth": 1
}

Sample response:
{
    "total_links": 469,
    "total_images": 5,
    "detailedLinks": [
        {
            "page_title": "GeeksforGeeks | A computer science portal for geeks",
            "page_link": "https://www.geeksforgeeks.org/",
            "image_count": 5,
            "depth": 1,
            "links_count": 469
        }
    ]
}

Required Work:
1) Acknowledgement token : Need to return acknowledgement token which can be returned using HATEOAS. HATEOAS gives us functionality to return link/unique id in response.
Using that unique id we can get  the  response after sometime.
2) Status API : We can maintain a hashmap or we can use  some cache to store the status of the request. Key of the map will be the unique id and value will be status.
This unique id will be same as which we are returning as acknowledgement token.






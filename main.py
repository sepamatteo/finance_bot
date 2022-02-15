import requests

def writeFile(path, content):

    file = open(path, 'w')
    content = str(content)
    file.write(content)
    file.close

def main():
    
    path = '/home/matteo/MEGAsync/code/Python/YHAPI/tesla.json'

    url = "https://yh-finance.p.rapidapi.com/auto-complete"

    querystring = {"q":"tesla","region":"US"}

    headers = {
        'x-rapidapi-host': "yh-finance.p.rapidapi.com",
        'x-rapidapi-key': "2f564ef340msh63781686c607c0fp1a8e2djsn348e1330ba97"
        }

    response = requests.request("GET", url, headers = headers, params = querystring)
    content = response.text

    print(response.text)

    writeFile(path, content)

if __name__ == "__main__":
    main()
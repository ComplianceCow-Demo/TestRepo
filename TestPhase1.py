import requests

def fetch_user(username):
    url = f"https://api.github.com/users/{username}"
    resp = requests.get(url, timeout=5)

    if resp.status_code != 200:
        raise RuntimeError(f"Error {resp.status_code}: {resp.text}")

    return resp.json()

print(fetch_user("octocat"))

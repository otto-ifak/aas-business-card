#! /usr/bin/env python3

import requests
from aas_test_engines import file

URL = 'http://localhost:8080'

response = requests.get(f"{URL}/contacts")
assert response.status_code == 200

for i in response.json():
    uuid = i['id']
    print(f"Removing {uuid}")
    response = requests.delete(f"{URL}/contacts/{uuid}")
    assert response.status_code == 200

response = requests.post(f"{URL}/contacts", json={
    'academicTitle': 'Dr.',
    'firstName': 'Peter',
    'lastName': 'Pan',
    'company': 'Neverland Inc.',
    'department': 'Lost Boys',
    'street': 'Peter Pan Street 1',
    'zipCode': '12345',
    'cityTown': 'Neverland',
    'nationalCode': 'NEV',
    'mail': 'peter@pan.com',
    'phone': '+1234455',
    'web': 'peter-pan.com',
})
assert response.status_code == 200
uuid = response.json()['id']
print(f"Created {uuid}")

response = requests.get(f"{URL}/{uuid}/submodel")
assert response.status_code == 200
env = {"submodels": [response.json()]}
result = file.check_json_data(env)
result.dump()
assert result.ok()

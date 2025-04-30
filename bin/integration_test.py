#! /usr/bin/env python3

import requests
from aas_test_engines import file
import unittest
from requests.auth import HTTPBasicAuth


URL = 'http://localhost:8080'


class PublicEndpointsTest(unittest.TestCase):

    def setUp(self):
        with requests.Session() as session:
            session.auth = HTTPBasicAuth('user', 'password')

            response = session.get(f"{URL}/contacts")
            self.assertEqual(response.status_code, 200)

            for i in response.json():
                uuid = i['id']
                print(f"Removing {uuid}")
                response = session.delete(f"{URL}/contacts/{uuid}")
                self.assertEqual(response.status_code, 200)

            response = session.post(f"{URL}/contacts", json={
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
            self.assertEqual(response.status_code, 200)
            self.uuid: str = response.json()['id']
            print(f"Created {self.uuid}")

    def test_get_submodel_public(self):
        response = requests.get(f"{URL}/{self.uuid}/submodel")
        self.assertEqual(response.status_code, 200)
        env = {"submodels": [response.json()]}
        result = file.check_json_data(env)
        result.dump()
        self.assertTrue(result.ok())

    def test_forbidden(self):
        for method in ['get', 'post', 'patch', 'delete', 'put']:
            response = requests.request(method, f'{URL}/contacts')
            self.assertEqual(response.status_code, 401)
            response = requests.request(method, f'{URL}/contacts/{self.uuid}')
            self.assertEqual(response.status_code, 401)


if __name__ == '__main__':
    unittest.main()

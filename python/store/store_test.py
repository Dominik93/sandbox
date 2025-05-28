import os.path
import unittest

from store import create_store, Storage


class StoreTestCase(unittest.TestCase):

    def test_should_store_json(self):
        store = create_store(Storage.JSON)
        store.store({"value": "sample"}, "file")
        self.assertTrue(os.path.isfile("file.json"))
        os.remove("file.json")

    def test_should_load_stored_json(self):
        store = create_store(Storage.JSON)
        self.assertEqual({"value": "sample"}, store.load(lambda: {}, "load"))

    def test_should_load_from_provider_json(self):
        store = create_store(Storage.JSON)
        self.assertEqual({"value": "sample"}, store.load(lambda: {"value": "sample"}, "empty"))
        os.remove("empty.json")

    def test_should_store_pickle(self):
        store = create_store(Storage.PICKLE)
        store.store({"value": "sample"}, "file")
        self.assertTrue(os.path.isfile("file.pkl"))
        os.remove("file.pkl")

    def test_should_load_stored_pickle(self):
        store = create_store(Storage.PICKLE)
        self.assertEqual({"value": "sample"}, store.load(lambda: {}, "load"))

    def test_should_load_from_provider_pickle(self):
        store = create_store(Storage.PICKLE)
        self.assertEqual({"value": "sample"}, store.load(lambda: {"value": "sample"}, "empty"))
        os.remove("empty.pkl")


if __name__ == '__main__':
    unittest.main()

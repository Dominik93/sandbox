import os.path
import unittest

from parameterized import parameterized

from store import create_store, Storage


def _clean(file):
    os.remove(file)


class StoreTestCase(unittest.TestCase):

    @parameterized.expand([
        [Storage.JSON, ".json"],
        [Storage.PICKLE, ".pkl"],
    ])
    def test_should_store(self, storage, extension):
        store = create_store(storage)
        store.store({"value": "sample"}, "file")
        self.assertTrue(os.path.isfile("file" + extension))
        _clean("file" + extension)

    @parameterized.expand([
        [Storage.JSON],
        [Storage.PICKLE],
    ])
    def test_should_load_stored(self, storage):
        store = create_store(storage)
        self.assertEqual({"value": "sample"}, store.load(lambda: {}, "load"))

    @parameterized.expand([
        [Storage.JSON, ".json"],
        [Storage.PICKLE, ".pkl"],
    ])
    def test_should_load_from_provider(self, storage, extension):
        store = create_store(storage)
        self.assertEqual({"value": "sample"}, store.load(lambda: {"value": "sample"}, "empty"))
        _clean("empty" + extension)


if __name__ == '__main__':
    unittest.main()

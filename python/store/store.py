import json
import os.path
import pickle


def create(name: str):
    if name == 'pickle':
        return PickleStore()
    if name == 'json':
        return JsonStore()
    raise Exception(f"Store {name} not found.")

class Store:

    def __init__(self, loader, saver):
        self.loader = loader
        self.saver = saver

    def load(self, supplier, storage):
        if os.path.isfile(storage):
            return self.loader(storage)
        obj = supplier()
        self.saver(obj, storage)
        return obj

    def store(self, obj, storage):
        self.saver(obj, storage)


class JsonStore(Store):

    def __init__(self):
        super().__init__(lambda storage: self._load(storage),
                         lambda obj, storage: self._store(obj, storage))

    def _store(self, obj, storage):
        with open(storage + ".json", 'w', encoding='utf-8') as f:
            json.dump(obj, f, ensure_ascii=False, indent=4)

    def _load(self, storage):
        with open(storage + ".json") as f:
            return json.load(f)


class PickleStore(Store):

    def __init__(self):
        super().__init__(lambda storage: self._load(storage),
                         lambda obj, storage: self._store(obj, storage))

    def _store(self, obj, storage):
        with open(storage + ".pkl", 'wb') as outp:
            pickle.dump(obj, outp, pickle.HIGHEST_PROTOCOL)

    def _load(self, storage):
        with open(storage + ".pkl", 'rb') as inp:
            return pickle.load(inp)

from store import create


def _pickle():
    store = create("pickle")

    store.store({"data": "value"}, "data")

    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))
    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))


def _json():
    store = create("json")

    store.store({"data": "value"}, "data")

    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))
    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))


if __name__ == "__main__":
    _json()
    _pickle()

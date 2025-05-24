from store import create_store, Storage


def _pickle():
    store = create_store(Storage.PICKLE)

    store.store({"data": "value"}, "data")

    store.store(["1", "2"], "list")

    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))
    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))


def _json():
    store = create_store(Storage.JSON)

    store.store({"data": "value"}, "data")

    store.store(["1", "2"], "list")

    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))
    print(str(store.load(lambda: {"data": "val_1"}, 'data2')))


if __name__ == "__main__":
    _json()
    _pickle()

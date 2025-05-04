import os
import random
import time
from multiprocessing import Pool


def run(delay):
    print(f"Process {os.getpid()} with delay: {delay} ")
    time.sleep(delay)


def main():
    pool = Pool()
    results = []
    results.append(pool.apply_async(run, [random.randint(1, 9)]))
    results.append(pool.apply_async(run, [random.randint(1, 9)]))
    results.append(pool.apply_async(run, [random.randint(1, 9)]))
    results.append(pool.apply_async(run, [random.randint(1, 9)]))
    for index, result in enumerate(results):
        result.get()
        print(f"Process {index} done.")


if __name__ == "__main__":
    main()

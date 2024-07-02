package main

import (
	"fmt"
	"os"
	"strconv"
	"sync"
	"time"
	"math/rand"
)

func test(i int, philosophersNumber int, C []int, prisem []*sync.Mutex) {
	if C[(i-1+philosophersNumber)%philosophersNumber] != 2 && C[i] == 1 && C[(i+1+philosophersNumber)%philosophersNumber] != 2 {
		C[i] = 2
		prisem[i].Lock()
	}
}

func philosopher(i int, mealsNumber int, wg *sync.WaitGroup, philosophersNumber int, mutex *sync.Mutex, prisem []*sync.Mutex, C []int) {
	var counter int = 1;
	for counter <= mealsNumber {
		mutex.Lock()
		C[i] = 1
		test(i, philosophersNumber, C, prisem)
		mutex.Unlock()

		prisem[i].Unlock()

		mutex.Lock()
		fmt.Printf("Philosopher number %d has eaten his %d meal\n", i, counter)
		C[i] = 0
		test((i-1+philosophersNumber)%philosophersNumber, philosophersNumber, C, prisem)
		test((i+1+philosophersNumber)%philosophersNumber, philosophersNumber, C, prisem)
		mutex.Unlock()
		time.Sleep(time.Millisecond * time.Duration(rand.Intn(100)))
		counter++
	}
	wg.Done()
}

func main() {
	arguments := os.Args
	philosophersNumber, _ := strconv.Atoi(arguments[1])
  mealsNumber, _ := strconv.Atoi(arguments[2])

	C := make([]int, philosophersNumber)

	var wg sync.WaitGroup
	wg.Add(philosophersNumber)

	var mutex sync.Mutex
	prisem := make([]*sync.Mutex, philosophersNumber)

	for i := range prisem {
		prisem[i] = &sync.Mutex{}
	}

	for i := 0; i < philosophersNumber; i++ {
		go philosopher(i, mealsNumber, &wg, philosophersNumber, &mutex, prisem, C)
	}
	wg.Wait()
}

package main

import (
	"fmt"
)

func sum[K comparable, V int64 | float64](m map[K]V) V {
	var s V
	for _, t := range m {
		s += t 
	}
	return s
}

func main() {
	m := map[string]int64 {
		"a" : 1,
		"b" : 2,
	}
	m1 := map[string]float64 {
		"a" : 3.14,
		"b" : 2.301,
	}
	fmt.Println(sum(m))
	fmt.Println(sum(m1))
}
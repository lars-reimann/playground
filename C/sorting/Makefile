OBJECTS = main.o mergesort.o quicksort.o timeit.o
FLAGS = -Wall

sorting: $(OBJECTS)
	gcc $(FLAGS) -o $@ $(OBJECTS)

main.o: main.c mergesort.h quicksort.h timeit.h
	gcc $(FLAGS) -c $<

mergesort.o: mergesort.c mergesort.h
	gcc $(FLAGS) -c $<

quicksort.o: quicksort.c quicksort.h
	gcc $(FLAGS) -c $<

timeit.o: timeit.c
	gcc $(FLAGS) -c $<

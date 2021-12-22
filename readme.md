Codility tasks for a new QA applying to Backbase

**EvictingQueue**
1) Implement a class which is able to consume maximum 100 elements. An element is being added to the tail. When 101 element comes, first is being deleted and last became 101 (2,3...101).
2) Class should be safe in multithreaded environment.
3) There are two public methods available:
   1) void **addElement**(E element) - adds element
   2) Collection<E> **pollAll**() - returns all elements and clear all
4) Add tests covering all the functions including concurrency.

**PredicateWaiter**
1) Implement a class which is getting Supplier<Boolean'> and waits for given time with given pooling period to fullfill given condition.
2) Class must be safe in multithreaded environment.
3) Available methods/constructors
   1) void **waitFor**(Supplier<Boolean'> supplier, int poolingTime, TimeUnit poolingUnit, int timeout,
      TimeUnit timeoutUnit)  - gets a condition to wait for
   2) public **PredicateWaiter**(ExeńcutorService executor) - a public constructor, with executor as a parameter
4) Add tests covering all functions including concurrency.



package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected RecursiveDoubleLinkedListImpl<T> previous;

   public RecursiveDoubleLinkedListImpl() {
   }

   public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
         RecursiveDoubleLinkedListImpl<T> previous) {
      super(data, next);
      this.previous = previous;
   }

   @Override
   public void insert(T element) {
      if (isEmpty()) {
         data = element;
         setNext(new RecursiveDoubleLinkedListImpl<>());
         setPrevious(new RecursiveDoubleLinkedListImpl<>());
         if (previous.isEmpty()) {
            previous = (new RecursiveDoubleLinkedListImpl<T>(null, null, previous));
         }
      } else {
         next.insert(element);
      }
   }

   @Override
   public void remove(T element) {
      if (isEmpty()) {
      } else {
         if (getData().equals(element)) {
            if (previous.isEmpty() && next.isEmpty()) {
               setData(null);
               setNext(null);
               setPrevious(null);
            } else {
               data = next.getData();
               next = next.next;
               if (!next.equals(null)) {
                  ((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
               }
            }
         } else {
            next.remove(element);

         }
      }

   }

   @Override
   public void insertFirst(T element) {
      if (isEmpty()) {
         insert(element);
      } else {
         T elem = data;
         setData(element);
         RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>(elem, null, null);
         node.next = next;
         node.previous = this;
         node.previous.setNext(node);
         ((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(node);

      }

   }

   @Override
   public void removeFirst() {
      if (!isEmpty()) {
         data = next.data;
         next = next.next;
         if (!next.equals(null)) {
            ((RecursiveDoubleLinkedListImpl<T>) next).previous = previous;
         }
      }
   }

   @Override
   public void removeLast() {
      if (next.isEmpty()) {
         this.data = null;
         next = null;
      } else {
         ((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
      }
   }

   public RecursiveDoubleLinkedListImpl<T> getPrevious() {
      return previous;
   }

   public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
      this.previous = previous;
   }

}

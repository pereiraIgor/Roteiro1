package adt.queue;

public class QueueImpl<T> implements Queue<T> {

   private T[] array;
   private int tail;

   @SuppressWarnings("unchecked")
   public QueueImpl(int size) {
      array = (T[]) new Object[size];
      tail = -1;
   }

   @Override
   public T head() {
      T saida = null;
      if (!isEmpty()) {
         saida = array[0];
      }
      return saida;
   }

   @Override
   public boolean isEmpty() {
      return tail == -1;
   }

   @Override
   public boolean isFull() {
      return tail == array.length - 1;
   }

   private void shiftLeft() {
      for (int i = 0; i < tail; i++) {
         array[i] = array[i + 1];
      }
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (!isFull()) {

         array[++tail] = element;
         //System.out.println(Arrays.toString(array) + " com tail em " + tail);
      } else {
         throw new QueueOverflowException();
      }

   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      T saida = null;
      if (!isEmpty()) {
         //System.out.println(Arrays.toString(array) + " com tail em " + tail + " e posicao inicial é  " + array[0]);
         saida = array[0];
         tail--;
         shiftLeft();
         //System.out.println(Arrays.toString(array));
      } else {
         throw new QueueUnderflowException();
      }
      return saida;
   }

}

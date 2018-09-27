package adt.stack;

public class StackImpl<T> implements Stack<T> {

   private T[] array;
   private int top;

   @SuppressWarnings("unchecked")
   public StackImpl(int size) {
      array = (T[]) new Object[size];
      top = -1;
   }

   @Override
   public T top() {
      T retorno;
      if (!isEmpty()) {
         retorno = array[top];
      } else {
         retorno = null;
      }
      return retorno;
   }

   @Override
   public boolean isEmpty() {
      return top == -1;
   }

   @Override
   public boolean isFull() {
      return top == array.length - 1;
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (!isFull()) {
         array[++top] = element;
      } else {
         throw new StackOverflowException();
      }

   }

   @Override
   public T pop() throws StackUnderflowException {
      T saida = null;
      if (!isEmpty()) {
         saida = array[top];
         array[top--] = null;
      } else {
         throw new StackUnderflowException();
      }
      return saida;

   }

}

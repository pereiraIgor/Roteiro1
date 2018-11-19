package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      return heigthAux(-1, root);
   }

   public int heigthAux(int camada, BSTNode<T> no) {
      if (!no.isEmpty()) {
         int leftMax = heigthAux(camada + 1, (BSTNode<T>) no.getLeft());
         int rightMax = heigthAux(camada + 1, (BSTNode<T>) no.getRight());
         if (leftMax > rightMax)
            return leftMax;
         else
            return rightMax;
      } else
         return camada;
   }

   @Override
   public BSTNode<T> search(T element) {
	  if(element == null) return new BSTNode<T>();
      return searchAux(root, element);
   }

   public BSTNode<T> searchAux(BSTNode<T> no, T element) {
      if (no.isEmpty() || no.getData().equals(element))
         return no;
      else {
         if (no.getData().compareTo(element) < 0)
            return searchAux((BSTNode<T>) no.getRight(), element);
         else
            return searchAux((BSTNode<T>) no.getLeft(), element);

      }
   }

   @Override
   public void insert(T element) {
	   if(element != null)
		   insertAux(root, element);
   }

   public void insertAux(BSTNode<T> no, T element) {
      if (no.isEmpty()) {
         no.setData(element);

         no.setLeft(new BSTNode<T>());
         no.getLeft().setParent(no);

         no.setRight(new BSTNode<T>());
         no.getRight().setParent(no);
      } else {
         if (no.getData().compareTo(element) < 0)
            insertAux((BSTNode<T>) no.getRight(), element);
         else
            insertAux((BSTNode<T>) no.getLeft(), element);
      }
   }

   @Override
   public BSTNode<T> maximum() {
      if (isEmpty())
         return null;
      return maximumAux(root);
   }

   public BSTNode<T> maximumAux(BSTNode<T> no) {
      BSTNode<T> aux = no;
      while (!aux.getRight().isEmpty())
         aux = (BSTNode<T>) aux.getRight();
      return aux;
   }

   @Override
   public BSTNode<T> minimum() {
      if (isEmpty())
         return null;
      return minimumAux(root);
   }

   public BSTNode<T> minimumAux(BSTNode<T> no) {
      BSTNode<T> aux = (BSTNode<T>) no;
      while (!aux.getLeft().isEmpty())
         aux = (BSTNode<T>) aux.getLeft();
      return aux;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> no = search(element);
      if (no.isEmpty())
         return null;
      return sucessorAux(no);

   }

   public BSTNode<T> sucessorAux(BSTNode<T> no) {
      if (!no.getRight().isEmpty())
         return minimumAux((BSTNode<T>) no.getRight());

      BSTNode<T> pai = (BSTNode<T>) no.getParent();
      while (pai != null && pai.getRight() == no) {
         no = pai;
         pai = (BSTNode<T>) pai.getParent();
      }
      return pai;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> no = search(element);
      if (no.isEmpty())
         return null;
      return predecessorAux(no);
   }

   public BSTNode<T> predecessorAux(BSTNode<T> no) {
      if (!no.getLeft().isEmpty())
         return maximumAux((BSTNode<T>) no.getLeft());

      BSTNode<T> pai = (BSTNode<T>) no.getParent();
      while (pai != null && pai.getLeft() == no) {
         no = pai;
         pai = (BSTNode<T>) pai.getParent();
      }
      return pai;
   }

   @Override
   public void remove(T element) {
      removeAux(search(element));
   }

   public void removeAux(BSTNode<T> node) {
      if (!node.isEmpty()) {
         if (node.isLeaf()) {
            node.setData(null);
            node.setLeft(null);
            node.setRight(null);
         } else if (haveOneChild(node)) {
            if (node != root) {
               if (node.getParent().getLeft() == node) {
                  if (node.getLeft().isEmpty()) {
                     node.getParent().setLeft(node.getRight());
                     node.getRight().setParent(node.getParent());
                  } else {
                     node.getParent().setLeft(node.getLeft());
                     node.getLeft().setParent(node.getParent());
                  }
               } else {
                  if (node.getLeft().isEmpty()) {
                     node.getParent().setRight(node.getRight());
                     node.getRight().setParent(node.getParent());
                  } else {
                     node.getParent().setRight(node.getLeft());
                     node.getLeft().setParent(node.getParent());
                  }
               }
            } else {
               if (root.getLeft().isEmpty())
                  root = (BSTNode<T>) root.getRight();
               else
                  root = (BSTNode<T>) root.getLeft();
            }
         } else {
            BSTNode<T> sucessor = sucessor(node.getData());
            node.setData(sucessor.getData());
            removeAux(sucessor);
         }
      }
   }
   
   public boolean haveOneChild(BSTNode<T> node) {
		return ((node.getLeft().isEmpty() && !node.getRight().isEmpty()) || (!node.getLeft().isEmpty() && node.getRight().isEmpty()));
	}

   @Override
   public T[] preOrder() {
      ArrayList<Comparable> array = new ArrayList<>();
      preOrderAux(root, array);
      return (T[]) array.toArray(new Comparable[size()]);
   }

   public void preOrderAux(BSTNode<T> no, ArrayList<Comparable> array) {
      if (!no.isEmpty()) {
         array.add(no.getData());
         preOrderAux((BSTNode<T>) no.getLeft(), array);
         preOrderAux((BSTNode<T>) no.getRight(), array);
      }
   }

   @Override
   public T[] order() {
      ArrayList<Comparable> array = new ArrayList<>();
      orderAux(root, array);
      return (T[]) array.toArray(new Comparable[size()]);
   }

   public void orderAux(BSTNode<T> no, ArrayList<Comparable> array) {
      if (!no.isEmpty()) {
         orderAux((BSTNode<T>) no.getLeft(), array);
         array.add(no.getData());
         orderAux((BSTNode<T>) no.getRight(), array);
      }
   }

   @Override
   public T[] postOrder() {
      ArrayList<Comparable> array = new ArrayList<>();
      postOrderAux(root, array);
      return (T[]) array.toArray(new Comparable[size()]);
   }

   public void postOrderAux(BSTNode<T> no, ArrayList<Comparable> array) {
      if (!no.isEmpty()) {
         postOrderAux((BSTNode<T>) no.getLeft(), array);
         postOrderAux((BSTNode<T>) no.getRight(), array);
         array.add(no.getData());
      }
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

}

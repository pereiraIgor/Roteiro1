package adt.queueDoubleLinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.queue.Queue;
import adt.queue.QueueDoubleLinkedListImpl;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;

public class StudentQueueDoubleLinkedListTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();
		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

		queue3.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueDoubleLinkedListImpl<>(4);
		queue2 = new QueueDoubleLinkedListImpl<>(2);
		queue3 = new QueueDoubleLinkedListImpl<>(10);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertFalse(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue1.enqueue(new Integer(10000));
		queue1.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testaPegarHeadColocandoElemento() throws QueueUnderflowException {
		queue3.dequeue();
		assertEquals(queue3.head(), null);
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
		assertEquals(new Integer(2), queue3.dequeue());
	}
	@Test
	public void testaQueue3() throws QueueOverflowException, QueueUnderflowException {
		queue3.enqueue(new Integer(3));
		queue3.enqueue(new Integer(4));
		queue3.enqueue(new Integer(5));
		queue3.enqueue(new Integer(6));
		queue3.enqueue(new Integer(7));
		queue3.enqueue(new Integer(8));
		queue3.enqueue(new Integer(9));
		queue3.enqueue(new Integer(10));
		queue3.enqueue(new Integer(11));
		queue3.dequeue();
		queue3.dequeue();
		assertEquals(queue3.head(), new Integer(4));
	}
	@Test(expected=QueueOverflowException.class)
	public void testaQueue31() throws QueueOverflowException {
		queue3.enqueue(new Integer(3));
		queue3.enqueue(new Integer(4));
		queue3.enqueue(new Integer(5));
		queue3.enqueue(new Integer(6));
		queue3.enqueue(new Integer(7));
		queue3.enqueue(new Integer(8));
		queue3.enqueue(new Integer(9));
		queue3.enqueue(new Integer(10));
		queue3.enqueue(new Integer(11));
		queue3.enqueue(new Integer(12));
		assertEquals(queue3.head(), new Integer(4));
	}
	@Test(expected=QueueUnderflowException.class)
	public void testaQueue32() throws QueueOverflowException, QueueUnderflowException {
		queue3.enqueue(new Integer(3));
		queue3.enqueue(new Integer(4));
		queue3.dequeue();
		queue3.dequeue();
		queue3.dequeue();
		queue3.dequeue();
		assertEquals(queue3.head(),null);
	}
}
import java.util.*;
import java.io.*;

class Node{
	public User data;
	public Node next;
	public Node(User d){
		this.data = d;
		this.next = null;
	}
}

public class LinkedList{
	public Node head;
	public LinkedList(){
		this.head = null;
	}
	public boolean isEmpty(){
		return this.head == null;
	}
	public int getLength(){
		Node curr = this.head;
		int length = 0;
		while(null != curr){
			curr = curr.next;
			length++;
		}
		return length;
	}
	public boolean isMember(User d){
		if(null == d){
			//System.out.println("is null");
			return false;
		}
		Node curr = this.head;
		while(null != curr){
			if(curr.data.userID.equals(d.userID)){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	public void insertRear(User d){
		if(this.isMember(d)){
			return;
		}
		Node newNode = new Node(d);
		if(this.isEmpty()){
			this.head = newNode;
			return;
		}
		Node curr = this.head;
		if(head.next == null){
			head.next = newNode;
			return;
		}
		while(curr.next != null){
			curr = curr.next;
		}
		curr.next = newNode;
		return;
	}
	public void insertFront(User d){
		if(this.isMember(d)){
			return;
		}
		Node newNode = new Node(d);
		newNode.next = this.head;
		this.head = newNode;
		this.head = newNode;
	}
	public void deleteElement(User d){
		if(!this.isMember(d)){
			return;
		}
		if(this.head.next == null){
			this.head = null;
			return;
		}
		Node curr = this.head;
		Node temp = this.head.next;
		while(!(temp.data.userID.equals(d.userID))){
			temp = temp.next;
			curr = curr.next;
		}
		if(temp.data.userID.equals(d.userID)){
			curr.next = temp.next;
			temp = temp.next;
		}
		return;
	}
}
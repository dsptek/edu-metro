/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import java.util.ArrayList;
import java.util.List;

public class IntPairList {
	//
	private List<IntPair> intPairs;

	public IntPairList() {

	}

	public IntPairList(int leftCount) {
		//
		this.intPairs = new ArrayList<IntPair>();

		for(int i=0; i<leftCount; i++) {
			intPairs.add(new IntPair(i+1, 0));
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("IntPairList{");
		sb.append("intPairs=").append(intPairs);
		sb.append('}');
		return sb.toString();
	}

	public static IntPairList getSample() {
		//
		IntPairList sample = new IntPairList(5);
		sample.increaseRight(1);
		sample.increaseRight(2);
		sample.increaseRight(3);
		sample.increaseRight(4);
		sample.increaseRight(5);

		return sample;
	}

	public void increaseRight(int left) {
		//
		intPairs.get(left-1).increaseRight();
	}

	public void decreaseRight(int left) {
		//
		intPairs.get(left-1).decreaseRight();
	}

	public List<IntPair> getList() {
		// 
		return intPairs;
	}

	public void setIntPairs(List<IntPair> intPairs) {
		this.intPairs = intPairs;
	}

	public boolean containsRight(int left) {
		//
		for(IntPair intPair : this.intPairs) {
			if (intPair.getLeft() == left) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return intPairs.size();
	}

	public static void main(String[] args) {
		//
		System.out.println(getSample());
	}
}
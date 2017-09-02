package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.StringTokenizer;

public class IntPair implements ValueObject {
    //
    private static final String PAIR_TOKEN = ":";
    private int left;
    private int right;

    public IntPair() {

    }

    public IntPair(int left, int right) {
        //
        this.left = left;
        this.right = right;
    }

    public IntPair(String pairString) {
        //
        StringTokenizer tokenizer = new StringTokenizer(pairString, PAIR_TOKEN);
        this.left = Integer.valueOf(tokenizer.nextToken());
        this.right = Integer.valueOf(tokenizer.nextToken());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntPair{");
        sb.append("left=").append(left);
        sb.append(", right=").append(right);
        sb.append('}');
        return sb.toString();
    }

    public static IntPair getSample() {
        //
        return new IntPair("3:5");
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static IntPair fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IntPair.class);
    }

    public String toPairString() {
        //
        return String.format("%d:%d", left, right);
    }

    public void increaseLeft() {
        //
        left++;
    }

    public void increaseRight() {
        //
        right++;
    }

    public void decreaseLeft() {
        //
        left--;
    }

    public void decreaseRight() {
        //
        right--;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
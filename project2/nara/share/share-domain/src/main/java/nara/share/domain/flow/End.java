package nara.share.domain.flow;

public class End extends FlowNode {
    //
    public End() {
        //
        super("End", FlowNodeType.End);
        super.setNext(null);
    }

    public End(String name) {
        //
        super(name, FlowNodeType.End);
        super.setNext(null);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("End{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static End getSample() {
        //
        return new End();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
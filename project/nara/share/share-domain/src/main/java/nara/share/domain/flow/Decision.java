package nara.share.domain.flow;

public class Decision extends FlowNode {
    //
    public Decision(String name) {
        //
        super(name, FlowNodeType.Decision);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Decision{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}

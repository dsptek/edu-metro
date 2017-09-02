package nara.share.domain.flow;

public class Action extends FlowNode {
    //
    public Action(String name) {
        //
        super(name, FlowNodeType.Action);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Action{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}

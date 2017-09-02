package nara.share.domain.flow;

public class Start extends FlowNode {
    //
    public Start() {
        //
        super("Start", FlowNodeType.Start);
    }

    public Start(String name) {
        //
        super(name, FlowNodeType.Start);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Start{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}

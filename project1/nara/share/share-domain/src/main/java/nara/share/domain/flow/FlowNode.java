package nara.share.domain.flow;

import nara.share.domain.ValueObject;

public abstract class FlowNode implements ValueObject {
    //
    private String name;
    private FlowTarget target;
    private FlowNodeType type;
    private FlowNode next;
    private FlowNode previous;

    protected FlowNode(String name, FlowNodeType type) {
        //
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlowNode{");
        sb.append("name='").append(name).append('\'');
        sb.append(", target=").append(target);
        sb.append(", type=").append(type);
        sb.append(", next=").append(next);
        sb.append(", previous=").append(previous);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlowNode getNext() {
        return next;
    }

    public void setNext(FlowNode next) {
        this.next = next;
    }

    public FlowNode getPrevious() {
        return previous;
    }

    public void setPrevious(FlowNode previous) {
        this.previous = previous;
    }

    public FlowNodeType getType() {
        return type;
    }

    public void setType(FlowNodeType type) {
        this.type = type;
    }

    public FlowTarget getTarget() {
        return target;
    }

    public void setTarget(FlowTarget target) {
        this.target = target;
    }
}

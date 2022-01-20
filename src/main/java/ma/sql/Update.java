
package ma.sql;


public class Update implements DBUpdateBehavior {
    
    private Object object;

    @Override
    public void performUpdateBehavior() {
        
    }

    public Update() {
    }

    public Update(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object + "";
    }
    
}

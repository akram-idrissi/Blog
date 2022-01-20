
package ma.sql;


public class Delete implements DBUpdateBehavior {
    
    private Object object;

    @Override
    public void performUpdateBehavior() {
        
    }

    public Delete() {
    }

    public Delete(Object object) {
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

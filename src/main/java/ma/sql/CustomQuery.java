
package ma.sql;


public class CustomQuery implements DBQueryBehavior{
    
    private String query;
    
    @Override
    public Object performQueryBehavior() {
        return null;
    }

    public CustomQuery() {
    }

    public CustomQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    @Override
    public String toString() {
        return query + "";
    }

}

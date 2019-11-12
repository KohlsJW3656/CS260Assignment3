public class Food extends HumResource {


    public Food() {

    }

    public void update() {
        dao.connect();
        dao.setAutoCommit(false);

        dao.commit();
        dao.disconnect();
    }

    public void delete() {
        dao.connect();
        dao.setAutoCommit(false);

        dao.commit();
        dao.disconnect();
    }

    public void insert() {
        dao.connect();
        dao.setAutoCommit(false);

        dao.commit();
        dao.disconnect();
    }
}

package reimburse;

import java.util.List;

public interface ReimburseDao {
    void updateReimburseStatus(int id);
    void deleteReimburse(int id);
    List<Reimburse> getReimbursesByPending();
    List<Reimburse> getReimbursesByApproved();
}

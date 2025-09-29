package fitness.service;

import java.util.List;
import fitness.vo.Fitness;

public interface FitnessService {
	public void create(Fitness fitness) throws Exception;
	public Fitness retrieve(String id) throws Exception;
	public void update(Fitness fitness) throws Exception;
	public void delete(String id) throws Exception;
	public List<Fitness> retrieveAll();
}

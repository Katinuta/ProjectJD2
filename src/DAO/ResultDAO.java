package DAO;

import DTO.Result;

/**Interface contains methods for access to table Result*/

public interface ResultDAO extends AbstractDAO<Result>{

	public Result findResultByTestUser(int testId, int userId);
	
}

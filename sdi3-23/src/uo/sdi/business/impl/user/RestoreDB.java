package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.persistence.util.Jpa;
import uo.sdi.util.random.ScriptReader;

public class RestoreDB implements Command<Object> {

	@Override
	public Object execute() throws BusinessException {
		try {
			for (String query : ScriptReader.getQueries()) {
				Jpa.getManager().createNativeQuery(query).executeUpdate();
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return null;
	}

}

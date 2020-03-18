package son.jun.Test.service;
import java.util.List;



import javax.inject.Inject;



import org.springframework.stereotype.Service;



import son.jun.Test.DAO.TestDAO;

import son.jun.Test.bean.TestBean;


@Service
public class TestServiceImpl implements TestService {

	@Inject
	private TestDAO dao;

	@Override
	public List<TestBean> test() throws Exception {
		// TODO Auto-generated method stub
		return dao.test();
	}

}
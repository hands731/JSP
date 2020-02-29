package command.approval;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DocumentDAO;
import VO.SimpleApprovalListVO;
import command.ComHandler;

public class acceptApprovalDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		int docid = Integer.parseInt(request.getParameter("docid"));
		String sender = request.getParameter("sender");
		
		DocumentDAO documentDAO = new DocumentDAO();
		documentDAO.acceptApproval(userid, docid, sender);
		
		//1.  userid�� ���� doctbl��ȸ�ؼ� ���� �������� personaltbl �߰�
		//2. �ۼ����� state����. �����⸦ ����������. ���� ���� �����ڰ� null�̰ų� des3�̶�� ������������ state�� ����.
		List<SimpleApprovalListVO> approvalList = documentDAO.approvalList(userid, "receiveList", 0,1);

		request.setAttribute("docList", approvalList);
		request.setAttribute("page", "/ApprovalDoc_List/Approval_ReceiveList.jsp");
	}

}

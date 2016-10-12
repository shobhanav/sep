package order_management;

import java.util.ArrayList;

public class RepManagementImpl implements RepManagementInterface {
	
	private ArrayList<Rep> reps = new ArrayList<Rep>();
	
	@Override
	public Rep createRep(String uname, String clientName) {
		// TODO Auto-generated method stub
		return new Rep(uname, clientName);
	}


	@Override
	public boolean addRep(Rep rep) {		
		if(rep != null)
			reps.add(rep);		
		return true;
	}

	@Override
	public ArrayList<Rep> listRep(String uname) {
		
		if(uname.equals("all")){
			return reps;
		}else{
			ArrayList<Rep> list = new ArrayList<Rep>();
			for(Rep rep: reps){
				if(rep.getUname().equals(uname)){
					list.add(rep);
				}
			}
			return list;
		}
	}


}

package com.dcone.dtss.model;

public class lucky_money {
         int l_id;
         int round;
         int total;
        public lucky_money(){}
         public lucky_money(int l_id, int round, int total) {
			super();
			this.l_id = l_id;
			this.round = round;
			this.total = total;
		}
		public int getL_id() {
			return l_id;
		}
		public void setL_id(int l_id) {
			this.l_id = l_id;
		}
		public int getRound() {
			return round;
		}
		public void setRound(int round) {
			this.round = round;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		
         
         
}

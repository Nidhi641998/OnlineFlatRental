package com.cg.onlineflatrental.dto;

public class TenantDto {
	public TenantDto() {
		super();
	}

		public TenantDto(int tenantId, String tenantName, int tenantAge, int flat_address) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.tenantAge = tenantAge;
		this.flat_address = flat_address;
	}

		private int tenantId;
		
		private String tenantName;
		
		private int tenantAge;

		private int flat_address;

		public int getTenantId() {
			return tenantId;
		}

		public void setTenantId(int tenantId) {
			this.tenantId = tenantId;
		}

		public String getTenantName() {
			return tenantName;
		}

		public void setTenantName(String tenantName) {
			this.tenantName = tenantName;
		}

		public int getTenantAge() {
			return tenantAge;
		}

		public void setTenantAge(int tenantAge) {
			this.tenantAge = tenantAge;
		}

		public int getFlat_address() {
			return flat_address;
		}

		public void setFlat_address(int flat_address) {
			this.flat_address = flat_address;
		}
	
}




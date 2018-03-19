package bill99.com.Study.spring.circlereference;

public class CirclereFerenceBeanB {
	private CirclereFerenceBeanA beanA;
	
//	public CirclereFerenceBeanB(CirclereFerenceBeanA beanA) {
//		this.beanA = beanA;
//	}

	public CirclereFerenceBeanA getBeanA() {
		return beanA;
	}

	public void setBeanA(CirclereFerenceBeanA beanA) {
		this.beanA = beanA;
	}
	
}

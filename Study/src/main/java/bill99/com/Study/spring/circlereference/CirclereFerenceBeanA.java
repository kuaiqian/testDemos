package bill99.com.Study.spring.circlereference;

public class CirclereFerenceBeanA {
	CirclereFerenceBeanB beanB;
	
//	public CirclereFerenceBeanA(CirclereFerenceBeanB beanB) {
//		this.beanB = beanB;
//	}

	public CirclereFerenceBeanB getBeanB() {
		return beanB;
	}

	public void setBeanB(CirclereFerenceBeanB beanB) {
		this.beanB = beanB;
	}
	
	
}

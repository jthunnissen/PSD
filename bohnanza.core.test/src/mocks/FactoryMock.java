package mocks;

import bohnanza.core.AbstractFactory;

public class FactoryMock extends AbstractFactory {

	public FactoryMock() {
		super(EBeanTypeMock.values());
	}

	@Override
	protected void fillStateTransistions() {
		// TODO Auto-generated method stub

	}

}

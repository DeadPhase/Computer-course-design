package �򵥹���ģʽ;

public class proFactory {
	public static Draw produce(String name) {
		if (name.equals("Բ��")) {
			return new DrawOval();
		}

		else if (name.equals("����")) {
			return new DrawRec();
		}

		else if (name.equals("���� ��")) {
			return new DrawTri();
		}

		else
			return null;
	}
}

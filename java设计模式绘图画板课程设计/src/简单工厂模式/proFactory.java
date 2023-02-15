package 简单工厂模式;

public class proFactory {
	public static Draw produce(String name) {
		if (name.equals("圆形")) {
			return new DrawOval();
		}

		else if (name.equals("矩形")) {
			return new DrawRec();
		}

		else if (name.equals("三角 形")) {
			return new DrawTri();
		}

		else
			return null;
	}
}

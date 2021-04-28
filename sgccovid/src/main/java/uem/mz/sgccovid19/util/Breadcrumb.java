package uem.mz.sgccovid19.util;

import java.util.List;

import org.zkoss.zhtml.A;
import org.zkoss.zhtml.H3;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;

public class Breadcrumb {

	public static void drawn(Component ui, String pagTitle,
			List<String> strLinks) {

		ui.getChildren().clear();

		H3 title = new H3();
		title.setParent(ui);

		Text strTitle = new Text(pagTitle);
		strTitle.setParent(title);

		Ul ul = new Ul();
		ul.setSclass("breadcrumb panel");
		ul.setParent(ui);

		for (String link : strLinks) {

			Li li = new Li();
			li.setParent(ul);

			if (link.equals(strLinks.get(strLinks.size() - 1))) {

				li.setSclass("active");

				Text strLink = new Text(link);
				strLink.setParent(li);

			} else {
				A a = new A();
				a.setParent(li);
				a.setDynamicProperty("href", "#");

				Text strLink = new Text(link);
				strLink.setParent(a);
			}
		}
	}
}
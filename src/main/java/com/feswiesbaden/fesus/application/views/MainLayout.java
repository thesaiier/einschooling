package com.feswiesbaden.fesus.application.views;

import com.feswiesbaden.fesus.application.data.entity.User;
import com.feswiesbaden.fesus.application.security.AuthenticatedUser;
import com.feswiesbaden.fesus.application.views.about.AboutView;
import com.feswiesbaden.fesus.application.views.enrollstudent.EnrollStudentView;
import com.feswiesbaden.fesus.application.views.helloadmin.HelloAdminView;
import com.feswiesbaden.fesus.application.views.hellouser.HelloUserView;
import com.feswiesbaden.fesus.application.views.studentdetail.StudentDetailView;
import com.feswiesbaden.fesus.application.views.teacherdetail.TeacherDetailView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */

public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        DarkthemeCheckbox darkthemeCheckbox = new DarkthemeCheckbox();
        darkthemeCheckbox.addClassNames(LumoUtility.AlignItems.END);
        addToNavbar(true, toggle, viewTitle, darkthemeCheckbox);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Einschulungssystem");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        //nav.addClassName("sideNavi");
        addClassName("sideNavi");

        if (accessChecker.hasAccess(AboutView.class)) {
            nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.HANDSHAKE_SOLID.create()));

        }
        /*if (accessChecker.hasAccess(HelloUserView.class)) {
            nav.addItem(new SideNavItem("Hello User", HelloUserView.class, LineAwesomeIcon.GLOBE_SOLID.create()));

        }
        if (accessChecker.hasAccess(HelloAdminView.class)) {
            nav.addItem(new SideNavItem("Hello Admin", HelloAdminView.class, LineAwesomeIcon.GLOBE_SOLID.create()));

        }*/

        if (accessChecker.hasAccess(EnrollStudentView.class)) {
            nav.addItem(new SideNavItem("Einschulungsformular", EnrollStudentView.class,
                    LineAwesomeIcon.USER_EDIT_SOLID.create()));

        }
        if (accessChecker.hasAccess(TeacherDetailView.class)) {
            nav.addItem(new SideNavItem("Lehrerübersicht", TeacherDetailView.class,
                    LineAwesomeIcon.CHALKBOARD_TEACHER_SOLID.create()));

        }
        if (accessChecker.hasAccess(StudentDetailView.class)) {
            nav.addItem(
                    new SideNavItem("Schülerübersicht", StudentDetailView.class, LineAwesomeIcon.SCHOOL_SOLID.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}

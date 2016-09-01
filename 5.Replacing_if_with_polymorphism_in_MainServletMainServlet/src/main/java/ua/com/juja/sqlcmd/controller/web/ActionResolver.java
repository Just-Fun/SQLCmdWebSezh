package ua.com.juja.sqlcmd.controller.web;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlcmd.controller.web.actions.ErrorAction;
import ua.com.juja.sqlcmd.service.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
@Component
public class ActionResolver {

    @Autowired
    private Service service;

    private List<Action> actions;

    public ActionResolver() {
        actions = new LinkedList<>();

//        Reflections reflections = new Reflections(ErrorAction.class.getPackage().getName());
        Reflections reflections = new Reflections("");
        Set<Class<? extends AbstractAction>> classes =
                reflections.getSubTypesOf(AbstractAction.class);

        for (Class<? extends AbstractAction> aClass : classes) {
            if (aClass.equals(ErrorAction.class)) {
                continue;
            }

            try {
//                commands.add(command.getConstructor(DatabaseManager.class, View.class).newInstance(manager, view));
                AbstractAction action = aClass.getConstructor(Service.class).newInstance(service);
                actions.add(action);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        actions.add(new ErrorAction(service));

    }

    public Action getAction(String url) {
        for (Action action : actions) {
            if (action.canProcess(url)) {
                return action;
            }
        }
        return new NullAction();
    }
}

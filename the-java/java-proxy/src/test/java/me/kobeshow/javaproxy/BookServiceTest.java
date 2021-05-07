package me.kobeshow.javaproxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {
        BookService bookService = new DefaultBookService();
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("rent")) {
                System.out.println("aaaaa");
                Object invoke = method.invoke(bookService, args);
                System.out.println("bbbbb");
                return invoke;
            }

            return method.invoke(bookService, args);
        }
    });

    @Test
    public void di() {
        MethodInterceptor handler = new MethodInterceptor() {
            BookService bookService = new DefaultBookService();
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("rent")) {
                    System.out.println("AAAA");
                    Object invoke = method.invoke(bookService, objects);
                    System.out.println("BBBB");
                    return invoke;
                }
                return method.invoke(bookService, objects);
            }
        };
        Enhancer.create(BookService.class, handler);

        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

    @Test
    public void byteBuddyTest() throws Exception {
        Class<? extends BookServiceClass> proxyClass = new ByteBuddy().subclass(BookServiceClass.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BookServiceClass bookService = new BookServiceClass();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("aaaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("bbbb");
                        return invoke;
                    }
                }))
                .make().load(BookServiceClass.class.getClassLoader()).getLoaded();
        BookServiceClass bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

}

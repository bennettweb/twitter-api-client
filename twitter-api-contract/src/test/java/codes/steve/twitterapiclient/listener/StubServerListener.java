package codes.steve.twitterapiclient.listener;

import codes.steve.twitterapiclient.commons.config.TwitterConfig;
import me.sbio.twitterstub.TwitterStubServer;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class StubServerListener extends RunListener {

    private TwitterConfig twitterConfig = new TwitterConfig();
    private TwitterStubServer twitterStubServer;

    @Override
    public void testRunStarted(Description description) throws Exception {
        twitterStubServer = new TwitterStubServer(twitterConfig.getPort());
        twitterStubServer.start();
        twitterStubServer.registerDefaultMappings(twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret());
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        twitterStubServer.stop();
    }
}

package htsjdk.tribble.readers;

import java.io.InputStream;
import java.io.StringReader;

/**
 * A collection of factories for generating {@link LineReader}s.
 *
 * @Deprecated use {@link SynchronousLineReader} directly.
 * @author mccowan
 */
@Deprecated
public class LineReaderUtil {
    @Deprecated
    public enum LineReaderOption {
        ASYNCHRONOUS,
        SYNCHRONOUS
    }

    /**
     * @Deprecated use <code>new SynchronousLineReader(stream);</code>
     */
    @Deprecated
    public static LineReader fromBufferedStream(final InputStream stream) {
        return new SynchronousLineReader(stream);
    }

    /**
     * @Deprecated use <code>new SynchronousLineReader(stringReader);</code>
     */
    @Deprecated
    public static LineReader fromStringReader(final StringReader stringReader) {
        return new SynchronousLineReader(stringReader);
    }

    /**
     * @Deprecated Asynchronous mode is not going to be supported. Use <code>new SynchronousLineReader(stringReader);</code>
     */
    @Deprecated
    public static LineReader fromStringReader(final StringReader stringReader, final Object ignored) {
        return new SynchronousLineReader(stringReader);
    }

    /**
     * Convenience factory for composing a LineReader from an InputStream.
     * @Deprecated Asynchronous mode is not going to be supported. Use <code>new SynchronousLineReader(bufferedStream);</code>
     */
    @Deprecated
    public static LineReader fromBufferedStream(final InputStream bufferedStream, final Object ignored) {
        return new SynchronousLineReader(bufferedStream);
    }

}
